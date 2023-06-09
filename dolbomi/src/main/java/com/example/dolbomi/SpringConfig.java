package com.example.dolbomi;

import com.example.dolbomi.repository.*;
import com.example.dolbomi.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository());
    }
    @Bean
    public LoginService loginService(){
        return new LoginService(teacherAccountRespository(), parentAccountRespository(), guardianRepository(), adminRepository());
    }

    @Bean
    public PickupService pickupService() {
        return new PickupService(studentRepository(), pickupRepository(), parentRepository(),
                guardianRepository(), teacherRepository(), pickupMessageRepository());
    }
    @Bean
    public StudentStateService studentStateService(){
        return new StudentStateService(studentStateRepository(), parentRepository(), studentRepository());
    }
    @Bean
    public TimelineService timelineService(){
        return new TimelineService(studentRepository(), studentScheduleRepository(), afterSchoolClassRepository(), studentTimeRepository());
    }
    @Bean
    public AdminService adminService(){
        return new AdminService(dolbomClassRepository(), studentRepository(), parentRepository(),
                teacherRepository(), afterSchoolClassRepository(), studentScheduleRepository(),
                studentTimeRepository(), studentStateRepository());
    }

    @Bean
    public NewsService newsService(){
        return new NewsService(newsRepository(), parentRepository());
    }
    @Bean
    public FileService fileService(){
        return new FileService((fileRepository()));
    }
    @Bean
    public AlbumService albumService(){
        return new AlbumService(albumRepository(), parentRepository());
    }

    @Bean
    public GuardianManageService guardianManageService()
    { return new GuardianManageService(guardianRepository(), studentRepository(),
            studentOfGuardianRepository());}

    @Bean
    public CallService callService(){
        return new CallService(teacherRepository(),parentRepository());
    }

    @Bean
    public AfterSchoolClassRepository afterSchoolClassRepository() {
        return new JdbcTemplateAfterSchoolClasstRepository(dataSource);
    }
    @Bean
    public StudentRepository studentRepository() {
        return new JdbcTemplateStudentRepository(dataSource);
    }
    @Bean
    public TeacherAccountRespository teacherAccountRespository() {
        return new JdbcTemplateTeacherAccountRespository(dataSource);
    }
    @Bean
    public ParentAccountRespository parentAccountRespository() {
        return new JdbcTemplateParentAccountRespository(dataSource);
    }
    @Bean
    public AdminRepository adminRepository() {
        return new JdbcTemplateAdminRepository(dataSource);
    }
    @Bean
    public StudentScheduleRepository studentScheduleRepository() {
        return new JdbcTemplateStudentScheduleRepository(dataSource);
    }
    @Bean
    public StudentStateRepository studentStateRepository() {
        return new JdbcTemplateStudentStateRepository(dataSource);
    }
    @Bean
    public StudentTimeRepository studentTimeRepository() {
        return new JdbcTemplateStudentTimeRepository(dataSource);
    }
    @Bean
    public GuardianRepository guardianRepository() {
        return new JdbcTemplateGuardianRepository(dataSource);
    }
    @Bean
    public PickupRepository pickupRepository(){
        return new MemoryPickupRepository();
    }
    @Bean
    public DolbomClassRepository dolbomClassRepository(){
        return new JdbcTemplateDolbomClassRepository(dataSource);
    }
    @Bean
    public ParentRepository parentRepository(){
        return new JdbcTemplateParentRepository(dataSource);
    }
    @Bean
    public TeacherRepository teacherRepository() { return new JdbcTemplateTeacherRepository(dataSource); }

    @Bean
    public NewsRepository newsRepository() { return new JdbcTemplateNewsRepository(dataSource);    }

    @Bean
    public FileRepository fileRepository() { return new JdbcTemplateFileRepository(dataSource);}

    @Bean
    public AlbumRepository albumRepository() { return new JdbcTemplateAlbumRepository(dataSource);}
    @Bean
    public StudentOfGuardianRepository studentOfGuardianRepository() { return new JdbcTemplateStudentOfGuardianRepository(dataSource); }
    @Bean
    public PickupMessageRepository pickupMessageRepository(){
        return new JdbcTemplatePickupMessageRepository(dataSource);
    }
}
