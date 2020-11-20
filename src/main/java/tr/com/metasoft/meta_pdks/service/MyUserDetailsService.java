package tr.com.metasoft.meta_pdks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.metasoft.meta_pdks.model.User;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
//        CustomUserDetails userDetails;
//
//        if (user != null){
//            userDetails = new CustomUserDetails();
//            userDetails.setUser(user);
//            return userDetails;
//        }else{
//            throw new UsernameNotFoundException("User not exist with: " + username);
//        }

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new CustomUserDetails(user);


//        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//        return buildUserForAuthentication(user, authorities);
    }

//    private List<GrantedAuthority> getUserAuthority(List<Role> userRoles) {
//        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//        for (Role role : userRoles) {
//            roles.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
//        return grantedAuthorities;
//    }
//
//    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                user.getActive(), true, true, true, authorities);
//    }
}

