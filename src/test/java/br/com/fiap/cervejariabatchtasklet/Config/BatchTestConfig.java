package br.com.fiap.cervejariabatchtasklet.Config;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// QUando rodamos um processo Spring ele ja Start o processo Job
// Se tivessemos um Job extardado por uma chamada externa utilizamos assim

@Configuration
public class BatchTestConfig {

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils(){
        return new JobLauncherTestUtils();
    }
}
