package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.repository.AttendDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendDateController {
    private final AttendDateRepository attendDateRepository;

    @GetMapping("/user/course/{cId}/dates/{page}/{size}")
    public String check(@PathVariable("cId") Long courseId, Model model,
                        @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("date")));
        List<AttendDate> attendDates = attendDateRepository.findAttendDatesByCourse_Id(courseId, pageRequest);
        model.addAttribute("attendDates", attendDates);
        model.addAttribute("courseId", courseId);
        return "date_selection";
    }
}
