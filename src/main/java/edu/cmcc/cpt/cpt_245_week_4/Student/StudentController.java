package edu.cmcc.cpt.cpt_245_week_4.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setEmail(rs.getString("email"));
            return student;
        });
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            student.setEmail(rs.getString("email"));
            return student;
        });
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail());
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail(), id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
