package me.dgahn.crs.classroom;

import lombok.RequiredArgsConstructor;
import me.dgahn.crs.account.Account;
import me.dgahn.crs.account.AccountDto.AccountSecurityDto;
import me.dgahn.crs.account.AccountService;
import me.dgahn.crs.reservation.Reservation;
import me.dgahn.crs.reservation.ReservationDto.ReservationRegisterFormDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class ClassroomController {

  private final ClassroomService classroomService;
  private final AccountService accountService;

  @GetMapping("/classrooms")
  public String classroomList(Model model) {
    List<Classroom> classrooms = classroomService.findAll();
    model.addAttribute("classrooms", classrooms);
    return "classrooms/classroomList";
  }

  @GetMapping("/classrooms/{classroomId}/reservations")
  public String classroomReservations(@AuthenticationPrincipal AccountSecurityDto subscriber,
                                      @PathVariable Long classroomId,
                                      Model model) {
    Account account = accountService.findByName(subscriber.getUsername());
    Classroom classroom = classroomService.findByClassroomId(classroomId);
    List<Reservation> reservations = classroomService.findClassroomReservationAll(classroomId);
    List<Account> accounts = accountService.findAll();
    model.addAttribute("currentUsername", account.getName());
    model.addAttribute("accounts", accounts);
    model.addAttribute("classroom", classroom);
    model.addAttribute("reservations", reservations);
    model.addAttribute("form", new ReservationRegisterFormDto());

    return "classrooms/classroomReservationList";
  }

  @PostMapping("/classrooms/{classroomId}/reservation")
  public String registerReservation(@PathVariable Long classroomId,
                                    @AuthenticationPrincipal AccountSecurityDto subscriber,
                                    ReservationRegisterFormDto formDto) {
    //ToDo 강의실 예약에 대해서 DB에 값 넣기
    classroomService.reservation(classroomId, subscriber, formDto);

    System.out.println("~~~~~~~~~~~~~~~~~~");
    System.out.println(formDto.getAssistants());
    System.out.println("~~~~~~~~~~~~~~~~~~");

    return "redirect:/classrooms/" + classroomId + "/reservations";
  }

}
