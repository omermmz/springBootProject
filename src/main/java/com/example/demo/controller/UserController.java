package com.example.demo.controller;

import com.example.demo.manager.CompanyUserManager;
import com.example.demo.manager.UserManager;
import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.ReservationUserIdDTO;
import com.example.demo.model.dto.UserInfoDTO;
import com.example.demo.model.dto.WhoAmIDTO;
import com.example.demo.model.request.UpdateMailRequest;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateUserInfoRequest;
import com.example.demo.service.UserService;
//import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class UserController {

    private final UserManager userManager;
    private final UserService userService;

    @GetMapping(path = "/companyuser")
    public List<CompanyUserIdDTO> getCompanyUsersByType(){
        return userManager.getCompanyUsersByType("Company Employee");
    }

    @GetMapping(path = "/reservationuser")
    public List<ReservationUserIdDTO> getReservationUsersByType(){
        return userManager.getReservationUsersByType("Reservation User");
    }

    @ResponseBody
    @GetMapping(path = "/whoAmI")
    public WhoAmIDTO whoAmI() {
        return userService.whoAmI();
    }


    @ResponseBody
    @GetMapping(path = "/getUserInfo")
    public UserInfoDTO getUserInfo(){
        return userService.getUserInfo();
    }
    @PutMapping(path = "/updateUserInfo")
    public void updateUserInfo(@RequestBody UpdateUserInfoRequest updateUserInfoRequest){
         userService.updateUserInfo(updateUserInfoRequest);
    }


    @PutMapping(path = "/updatePassword")
    public String updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,HttpServletResponse response){

        String resp =  userService.updatePassword(updatePasswordRequest);
        if(resp=="Uyusmazlik"){
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return "Sifreler Uyusmuyor";
        }else{
            response.setStatus(HttpServletResponse.SC_OK);
            return "Basarili";
        }
    }
    @PutMapping(path = "/updateMail")
    public String updateMail(@RequestBody UpdateMailRequest updateMailRequest){
        return userService.updateMail(updateMailRequest);
    }



  /*  @GetMapping(path = "/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public String logout(){
        return "Okey";
    }*/

    @GetMapping("/logout")
    public ResponseEntity<String> viewlogout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return new ResponseEntity<>("signed-out", HttpStatus.OK);
    }

    //TODO:SET COOKIE METHOD
    // @GetMapping("/login")
  //  public String login(HttpServletResponse response){
  //      setDummyCookie(response);
  //      return "login successfully";
  //  }
  //  private void setDummyCookie(HttpServletResponse response){
   //     Cookie cookie = new Cookie("mycookie","cookiee");
    //    cookie.setMaxAge(2592000);
    //    cookie.setPath("/");



      //   response.addCookie(cookie);
  //  }

}
