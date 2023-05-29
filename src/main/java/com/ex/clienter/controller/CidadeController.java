package com.ex.clienter.controller;

import java.util.List;

import javax.validation.Valid;

import com.ex.clienter.dto.RequisicaoNovaCidade;
import com.ex.clienter.dto.RequisicaoNovoCliente;
import com.ex.clienter.model.Cidade;
import com.ex.clienter.repository.CidadeRepository;

import com.ex.clienter.util.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cidade")
public class CidadeController {
    
    @Autowired
    private CidadeRepository cidadeRepository;

	@Autowired
	private BlobUtils blobUtils;

    @GetMapping("formulario")
	public ModelAndView formulario(RequisicaoNovaCidade requisicao) {
		ModelAndView mv = new ModelAndView("cidade/formulario");
		return mv;
	}

    @PostMapping("novo")
	public String novo(@Valid RequisicaoNovaCidade requisicao, BindingResult result) {
		if(result.hasErrors()){
			return "cidade/formulario";
		}
		
		Cidade cidade = requisicao.toCidade();
		cidadeRepository.save(cidade);
		blobUtils.salvarMensagemSucessoBlob(cidade);
		return "redirect:/home";
	}

	@GetMapping("consulta")
	public ModelAndView consulta(RequisicaoNovoCliente requisicao) {
		List<Cidade> cidades = cidadeRepository.findAll();
		ModelAndView mv = new ModelAndView("cidade/consulta");
		mv.addObject("cidades", cidades);
		return mv;
	}

	@PostMapping("/{busca}")
	public ModelAndView buscar(String radio, String itemBusca) {
		System.out.println(radio.equals("nomeCidade"));
		List<Cidade> cidades = null;
		if(radio.equals("nomeCidade")){
			cidades = cidadeRepository.findByNomeCidade(itemBusca);
		}
		else {
			cidades = cidadeRepository.findByEstado(itemBusca);
		}
	    ModelAndView mv = new ModelAndView("cidade/consulta");
	    mv.addObject("cidades", cidades);
	    return mv; 
	} 

	

}
