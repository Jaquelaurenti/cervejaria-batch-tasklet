package br.com.fiap.cervejariabatchtasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Paths;

@SpringBootApplication
@EnableBatchProcessing // habilita o spring batch
public class CervejariaBatchChunkApplication {

	Logger logger = LoggerFactory.getLogger(CervejariaBatchChunkApplication.class);

	@Bean
	public FlatFileItemReader<Pessoa> itemReader(@Value("file.input") Resource resource){
		return new FlatFileItemReaderBuilder<Pessoa>()
				.delimited().delimiter(";")
				.names("nome", "cpf")
				.resource(resource)
				.targetType(Pessoa.class)
				.name("File item Reader")
				.build();

	}

	//Item Processor recebe 2 pojos, de onde da vindo e para onde vai
	// neste caso class pessoa para a tabela pessoa

	@Bean
	public ItemProcessor<Pessoa, Pessoa> itemProcessor(){
		return pessoa->{
			pessoa.setNome((pessoa.getNome().toUpperCase()));
			pessoa.setCpf((pessoa.getCpf()
					.replaceAll("\\.","")
					.replace("-", "")));
		 	return pessoa;
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CervejariaBatchChunkApplication.class, args);
	}

}
