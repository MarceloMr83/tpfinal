package com.poo.tpfinal.configuration;
import com.poo.tpfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/bookingDetail/**").hasRole("USER")                
                .and()
                .formLogin();
}




    

}














 /*
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/adduser").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/booking").permitAll()
                .antMatchers("/bookingDetail").hasRole("USER")
                .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                .and()
                .logout()
                .permitAll();
    }
    @Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(UserDetailsService)
        .passwordEncoder(getPasswordEncoder());
}
    @Autowired
    private DataSource dataSource;

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    }
 /*   
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select email,password,idUser from user where email=?")
            .aut
            /*.authoritiesByUsernameQuery(
                    "SELECT email, 'ROLE_USER' FROM user WHERE email=?");*/
        
  //  }   */
//}