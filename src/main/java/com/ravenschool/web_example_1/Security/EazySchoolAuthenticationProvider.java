package com.ravenschool.web_example_1.Security;

import com.ravenschool.web_example_1.Model.Person;
import com.ravenschool.web_example_1.Repository.IPersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class EazySchoolAuthenticationProvider implements AuthenticationProvider {

    private final IPersonRepository _personRepository;
    private final PasswordEncoder _passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // validate the email (check if it exist in DB)
        Person person = _personRepository.getByEmail(email);
        // validate the password
        if (person != null && person.getPersonId() > 0 && _passwordEncoder.matches(password, person.getPwd()))
            return new UsernamePasswordAuthenticationToken(person.getName(), null,
                    getGrantedAuthority(person.getRole().getRoleName()));
        else
            throw new BadCredentialsException("Invalid Credentials");
    }

    private List<GrantedAuthority> getGrantedAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
