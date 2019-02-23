package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.repository.CategoriaRepository;
import br.edu.utfpr.jsf.util.FacesUtil;
import br.edu.utfpr.jsf.util.MessageUtil;

@Component
public class CategoriaConverter implements Converter {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private MessageUtil messageUtil;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return repository.findById(Integer.parseInt(value)).orElse(null);
		} catch (Exception ex) {
			throw new ConverterException(FacesUtil.criarMensagemErro(messageUtil.getMessage("categoria.invalida")));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Categoria) {
			Categoria categoria = (Categoria) value;
			return String.valueOf(categoria.getCodigo());
		} else {
			return null;
		}
	}

}
