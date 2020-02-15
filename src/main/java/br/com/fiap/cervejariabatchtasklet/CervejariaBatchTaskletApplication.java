/*package br.com.fiap.cervejariabatchtasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Paths;

@SpringBootApplication
@EnableBatchProcessing // habilita o spring batch
public class CervejariaBatchTaskletApplication {

	Logger logger = LoggerFactory.getLogger(CervejariaBatchTaskletApplication.class);

	// Utilizamos a notacao @Qualifier para nomear qual tasklet sera executa
	// no caso de houver varias tasklet no mesmo arquivo
	// Tasklet sao execucoes unicas
	// os chunks pode haver diversas regras para execucoes diversas (assincrono)

	@Bean
	public Tasklet clearFiles(@Value("${file.local}") String path) {
		return (contribution, chuckContext) -> {
			File file = Paths.get(path).toFile();
			if(file.delete()){
				logger.info("Arquivo Deletado");

			}else{
				logger.error("Nao foi possivel deletar o arquivo");
			}
			return  RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Step step(StepBuilderFactory stepBuilderFactory,
					 Tasklet tasklet){
		return stepBuilderFactory.get("Delete file Step")
				.tasklet((tasklet))
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step step){
		return jobBuilderFactory.get("Delete file Job")
				.start(step)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(CervejariaBatchTaskletApplication.class, args);
	}

}
*/