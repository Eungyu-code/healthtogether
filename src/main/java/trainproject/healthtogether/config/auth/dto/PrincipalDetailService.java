package trainproject.healthtogether.config.auth.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainproject.healthtogether.domain.user.User;
import trainproject.healthtogether.repository.UserRepository;

@Service  //Bean 등록
@RequiredArgsConstructor
@Transactional
public class PrincipalDetailService  implements UserDetailsService {

    private  final UserRepository userRepository;

    /**스프링이 로그인 요청을 가로챌 때, username, passwrod 변수 2개를 가로채는데
     password 부분처리는 알아서 함.
     username (email) 이 DB 에 있는지만 확인해주면 됨. return
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User principal=userRepository.findByEmail(email).get();
        if(principal==null){
            throw new UsernameNotFoundException(email);
        }
        UserDetails result= org.springframework.security.core.userdetails.User.builder()
                .username(principal.getEmail())
                .password(principal.getPicture())
                .roles(principal.getRole().toString())
                .build();

        return result;
    }

}
