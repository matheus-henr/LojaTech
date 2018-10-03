package br.com.lojatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lojatech.domain.PagamentoComBoleto;
import br.com.lojatech.domain.PagamentoComCartao;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder(){
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper mapper){
				mapper.registerSubtypes(PagamentoComBoleto.class);
				mapper.registerSubtypes(PagamentoComCartao.class);
				super.configure(mapper);
			} 
		};
		
		return builder;
	}
}
