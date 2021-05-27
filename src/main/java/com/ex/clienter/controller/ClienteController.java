package com.ex.clienter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.ex.clienter.dto.RequisicaoNovoCliente;
import com.ex.clienter.model.Cidade;
import com.ex.clienter.model.Cliente;
import com.ex.clienter.repository.CidadeRepository;
import com.ex.clienter.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

	@Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("formulario")
	public ModelAndView formulario(RequisicaoNovoCliente requisicao) {
		List<Cidade> cidades = cidadeRepository.findAll();
		ModelAndView mv = new ModelAndView("cliente/formulario");
		mv.addObject("cidades", cidades);
		return mv;
	}

    @PostMapping("novo")
	public String novo(@Valid RequisicaoNovoCliente requisicao, BindingResult result) {
		if(result.hasErrors()){
			return "cliente/formulario";
		}
		
		Cliente cliente = requisicao.toCliente();
		clienteRepository.save(cliente);
		return "redirect:/home";
	}

	@GetMapping("consulta")
	public ModelAndView consulta(RequisicaoNovoCliente requisicao) {
		List<Cliente> clientes = clienteRepository.findAll();
		ModelAndView mv = new ModelAndView("cliente/consulta");
		mv.addObject("clientes", clientes);
		return mv;
	}

    @PostMapping("deletar")
	public String deletar(String id) {
        System.out.println(id);
        clienteRepository.deleteById(Long.parseLong(id));
		return "redirect:/cliente/consulta";
	}

	@PostMapping("/{busca}")
	public ModelAndView buscar(String radio, String itemBusca) {
		
		List<Cliente> clientes;
		if(radio.equals("nomeCliente")){
			clientes = clienteRepository.findByNomeCliente(itemBusca);
		}
		else {	
			clientes = new ArrayList<Cliente>();
			try {
				clientes.add(clienteRepository.findById(Long.parseLong(itemBusca)).get());
				} catch (Exception e) {}
		}
	    ModelAndView mv = new ModelAndView("cliente/consulta");
	    mv.addObject("clientes", clientes);
	    return mv; 
	} 

	@PostMapping("/alterar")
	public String alterar(String novoNome, String id) {
		

		Cliente novo = clienteRepository.findById(Long.parseLong(id)).get();
		novo.setNomeCliente(novoNome);
		clienteRepository.save(novo);
		return "redirect:/cliente/consulta";
	}
    
}
