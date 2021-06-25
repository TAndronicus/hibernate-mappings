package jb;

import jb.service.ThreadStatesMachineFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class StateMachineConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ThreadStatesMachineFactory threadStatesMachineFactory() {
        return new ThreadStatesMachineFactory();
    }
}
