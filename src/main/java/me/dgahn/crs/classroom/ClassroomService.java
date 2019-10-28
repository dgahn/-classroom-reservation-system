package me.dgahn.crs.classroom;

import lombok.RequiredArgsConstructor;
import me.dgahn.crs.account.Account;
import me.dgahn.crs.account.AccountDto.AccountSecurityDto;
import me.dgahn.crs.account.AccountRepository;
import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.config.exception.RequestErrorException;
import me.dgahn.crs.reservation.Reservation;
import me.dgahn.crs.reservation.ReservationDto.ReservationRegisterFormDto;
import me.dgahn.crs.reservation.ReservationRepository;
import me.dgahn.crs.util.SimpleDateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClassroomService {

  private final ClassroomRepository classroomRepository;
  private final AccountRepository accountRepository;
  private final ReservationRepository reservationRepository;

  List<Classroom> findAll() {
    Optional<List<Classroom>> result = classroomRepository.findAll();
    if (result.isEmpty()) {
      return new ArrayList<>();
    }

    return result.get();
  }

  List<Reservation> findClassroomReservationAll(Long classroomId) {
    Optional<Classroom> optionalClassroom = classroomRepository.findBydClassroomId(classroomId);
    if (optionalClassroom.isEmpty()) {
      return new ArrayList<>();
    }

    Classroom classroom = optionalClassroom.get();
    List<Reservation> reservations = classroom.getReservations();

    return reservations;
  }


  public Classroom findByClassroomId(Long classroomId) {
    Optional<Classroom> optionalClassroom = classroomRepository.findBydClassroomId(classroomId);
    if (optionalClassroom.isEmpty()) {
      throw new RequestErrorException(204, "존재하지은 않는 강의실입니다.");
    }

    return optionalClassroom.get();
  }

  @Transactional
  public void reservation(Long classroomId,
                          AccountSecurityDto subscriberDto,
                          ReservationRegisterFormDto formDto) {
    // 예약자 정보 찾기기
    Optional<Account> optionalSubscriber = accountRepository.findByName(subscriberDto.getUsername());
    if (optionalSubscriber.isEmpty()) {
      throw new RequestErrorException(HttpStatus.NO_CONTENT.value(), "사용자의 정보가 존재하지 않습니다.");
    }

    // 참가자 정보 찾기
    List<Account> assistants = getAccountsFromName(formDto.getAssistants());

    // 강의실 정보 찾기
    Optional<Classroom> optionalClassroom = classroomRepository.findBydClassroomId(classroomId);
    if (optionalClassroom.isEmpty()) {
      throw new RequestErrorException(HttpStatus.NO_CONTENT.value(), "강의실 정보가 존재하지 않습니다.");
    }

    // 예약자를 참가자에 포함시키기
    Account subscriber = optionalSubscriber.get();
    assistants.add(subscriber);

    // AccountReservation 엔티티 생성하기
    List<AccountReservation> accountReservations = new ArrayList<>();
    assistants.forEach(e -> accountReservations.add(AccountReservation.createAccountReservation(e)));

    // 시작시간 만들기
    LocalDateTime startDate = SimpleDateTimeUtils.getLocalDateTime(formDto.getStartDate(), formDto.getStartHour(), formDto.getStartMinute());

    // 종료시간 만들기
    LocalDateTime endDate = SimpleDateTimeUtils.getLocalDateTime(formDto.getEndDate(), formDto.getEndHour(), formDto.getEndMinute());

    validateStartAndEndDate(startDate, endDate);

    // Reservation 만들기
    Reservation reservation = Reservation.createReservation(subscriber, optionalClassroom.get(), startDate, endDate, formDto.getTitle(), accountReservations);

    // Reservation 저장
    reservationRepository.save(reservation);

  }

  private void validateStartAndEndDate(final LocalDateTime startDate, final LocalDateTime endDate) {
    if (!startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
      throw new RequestErrorException(HttpStatus.BAD_REQUEST.value(), "시작시간이 종료시간보다 앞서야합니다.");
    }

    Optional<List<Reservation>> optionalReservations = reservationRepository.findByBetweenDate(startDate, endDate);
    System.out.println(optionalReservations.isPresent());
    System.out.println(optionalReservations.isEmpty());
    if (optionalReservations.isPresent()) {
      throw new RequestErrorException(HttpStatus.BAD_REQUEST.value(), "다른 일정과 겹칩니다. 일정을 다시 선택해주세요.");
    }
  }

  private List<Account> getAccountsFromName(final String assistants) {
    List<String> names = Arrays.stream(assistants.split(","))
                               .collect(Collectors.toList());
    Optional<List<Account>> optionalAccounts = accountRepository.findByNames(names);
    if (optionalAccounts.isEmpty()) {
      throw new RequestErrorException(400, "강사 및 조교의 이름을 확인해주세요.");
    }

    return optionalAccounts.get();
  }

}
