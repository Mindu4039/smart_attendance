package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddCourseDto;
import graduation_project.smart_attendance.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course findCourse(Long courseId){
        return courseRepository.findById(courseId).get();
    }

    public Course findCourseByName(Account account, String courseName){
        return courseRepository.findByCourseNameAndAccount(courseName, account);
    }

    @Transactional
    public List<Course> findCourses(Account account){
        List<Course> courses = courseRepository.findAllByAccount_Id(account.getId());
        return courses;
    }

    @Transactional
    public Long course(AddCourseDto addCourseDto, Account account){
        Course course = Course.createCourse(addCourseDto, account);
        courseRepository.save(course);
        return course.getId();
    }

    @Transactional
    public void deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
    }
}
