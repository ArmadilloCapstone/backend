package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Admin;
import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.form.ChangePwForm;
import com.example.dolbomi.form.LoginForm;
import com.example.dolbomi.form.SignupForm;
import com.example.dolbomi.form.TeacherLoginForm;
import com.example.dolbomi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class LoginController {
    private final LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginForm loginForm){
        //System.out.println(loginForm);
        if(loginForm.getOption() == 1){ // option 1 . teacher
            List<TeacherLoginForm> list = loginService.LoginT(loginForm.getUser_id(), loginForm.getUser_pw());
            if(list.size() == 1) return list.get(0);

        }
        if(loginForm.getOption() == 2){ // option 2 . parent
            List<Parent> list = loginService.LoginP(loginForm.getUser_id(), loginForm.getUser_pw());
            if(list.size() == 1) return list.get(0);
        }
        if(loginForm.getOption() == 3){ // option 3 . guardian
            List<Guardian> list = loginService.LoginG(loginForm.getSerial_num());
            if(list.size() == 1) return list.get(0);
        }
        if(loginForm.getOption() == 4){ // option 4 . admin
            List<Admin> list = loginService.LoginA(loginForm.getUser_id(), loginForm.getUser_pw());
            if(list.size() == 1) return list.get(0);
        }




        ErrorObject error = new ErrorObject();
        error.setName("Error");
        return error;
    }

    @PostMapping("/changepw")
    public String signup(@RequestBody ChangePwForm changeForm) {
        //System.out.println(changeForm);
        if(changeForm.getOption() == 1){ // option 1 . teacher
            Boolean b = loginService.changePwT(changeForm.getUser_id(), changeForm.getUser_pw(), changeForm.getUser_new_pw());
            if(b == true){
                return "success";
            }
            else{
                return "error";
            }
        }
        if(changeForm.getOption() == 2){ // option 2 . parent
            Boolean b = loginService.changePwP(changeForm.getUser_id(), changeForm.getUser_pw(), changeForm.getUser_new_pw());
            if(b == true){
                return "success";
            }
            else{
                return "error";
            }
        }
        if(changeForm.getOption() == 4){ // option 4 . admin
            Boolean b = loginService.changePwA(changeForm.getUser_id(), changeForm.getUser_pw(), changeForm.getUser_new_pw());
            if(b == true){
                return "success";
            }
            else{
                return "error";
            }
        }


        return "error";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupForm signupForm) {
        //System.out.println(signupForm);
        if(signupForm.getOption() == 1){ // option 1 . teacher
            String result = loginService.signupT(signupForm.getUser_id(), signupForm.getUser_pw(), signupForm.getName(), signupForm.getPhone_num());
            return result;
        }
        if(signupForm.getOption() == 2){ // option 2 . parent
            String result = loginService.signupP(signupForm.getUser_id(), signupForm.getUser_pw(), signupForm.getName(), signupForm.getPhone_num());
            return result;
        }
        if(signupForm.getOption() == 3){ // option 3 . guardian 사용되지 않음
            Boolean b = loginService.signupG(signupForm.getSerical_num(), signupForm.getName(), signupForm.getInfo());
            if(b == true){
                return "success";
            }
            else{
                return "error";
            }
        }


        return "error";
    }

}
