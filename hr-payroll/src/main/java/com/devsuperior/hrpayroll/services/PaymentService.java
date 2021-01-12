package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(Long workerId, int days) {
		
		Map<String, String> uriVariables = new HashMap<>();
		
		/* essa lógica de código faz com que o {id} abaixo seja substituido dinamicamente pelo workerId
		   em teoria poderia ser passado apenas concatenando, porém dessa forma fica uma sintaxe
		   mais bonita e pode ate mesmo evitar falhas de concatenação
		*/
		
		uriVariables.put("id", ""+workerId);
		// faz a request
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	
	}
}
