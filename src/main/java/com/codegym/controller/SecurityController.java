package com.codegym.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
   private String getPrincipal() {
      String username = null;
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (principal instanceof UserDetails) {
         username = ((UserDetails) principal).getUsername();
      } else {
         username = principal.toString();
      }
      return username;
   }

   @GetMapping(value = {"/", "/home"})
   public ModelAndView homePage() {
      return new ModelAndView("/welcome", "user", getPrincipal());
   }

   @GetMapping("/admin")
   public ModelAndView adminPage() {
      return new ModelAndView("/admin", "user", getPrincipal());
   }

   @GetMapping("/accessDenied")
   public ModelAndView accessDeniedPage() {
      return new ModelAndView("/access-denied", "user", getPrincipal());
   }

   @GetMapping( "/dba")
   public ModelAndView dbaPage() {
      return new ModelAndView("/dba", "user", getPrincipal());
   }
}
