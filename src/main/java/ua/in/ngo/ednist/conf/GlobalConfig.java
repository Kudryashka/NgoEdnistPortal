package ua.in.ngo.ednist.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:ua/in/ngo/ednist/conf/security-context.xml")
public class GlobalConfig {

}
