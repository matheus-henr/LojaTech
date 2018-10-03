package br.com.lojatech.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.lojatech.domain.Cliente;
import br.com.lojatech.dto.ClienteDTO;
import br.com.lojatech.repository.ClienteRepository;
import br.com.lojatech.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	HttpServletRequest request;
	
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping
				.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer id = Integer.parseInt(map.get("id"));

		Cliente aux = repository.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(id)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
