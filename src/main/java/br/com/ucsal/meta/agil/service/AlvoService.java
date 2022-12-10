package br.com.ucsal.meta.agil.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ucsal.meta.agil.dto.alvo.AlvoAplicacaoDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoCamadaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoPerguntaDTO;
import br.com.ucsal.meta.agil.dto.alvo.AlvoTemaDTO;
import br.com.ucsal.meta.agil.entity.AplicacaoEntity;
import br.com.ucsal.meta.agil.entity.CamadaEntity;
import br.com.ucsal.meta.agil.entity.PerguntaEntity;
import br.com.ucsal.meta.agil.entity.RespostaEntity;
import br.com.ucsal.meta.agil.entity.TemaEntity;
import br.com.ucsal.meta.agil.repository.AvaliacaoNotaViewRepository;

@Service
public class AlvoService {
	
	@Autowired
	private AvaliacaoNotaViewRepository notaRepository;

	
	public List<AlvoAplicacaoDTO> entityListToAplicacaoDTOList(List<AplicacaoEntity> aplicacoes) {
		
		List<AlvoAplicacaoDTO> alvoAplicacoes = new ArrayList<AlvoAplicacaoDTO>();
		
		for(AplicacaoEntity a : aplicacoes) {
			AlvoAplicacaoDTO dto = new AlvoAplicacaoDTO();		
			dto.setCdAplicacao(a.getCdAplicacao().intValue());
			dto.setLabel(a.getNmAplicacao());
			dto.setChildren(entityListToCamadaDTOList(a.getCamadas()));		
			alvoAplicacoes.add(dto);
		}
		
		return alvoAplicacoes;
	}

	public ArrayList<AlvoCamadaDTO> entityListToCamadaDTOList(List<CamadaEntity> camadas) {
		
		ArrayList<AlvoCamadaDTO> alvoCamadas = new ArrayList<AlvoCamadaDTO>();
		
		for(CamadaEntity c : camadas) {
			AlvoCamadaDTO dto = new AlvoCamadaDTO();
			dto.setLabel(c.getNmCamada());		
			dto.setChildren(entityListToTemaDTOList(c.getTemas()));		
			alvoCamadas.add(dto);
		}
		
		return alvoCamadas;
	}

	private ArrayList<AlvoTemaDTO> entityListToTemaDTOList(List<TemaEntity> temas) {
		
		ArrayList<AlvoTemaDTO> alvoTemas = new ArrayList<AlvoTemaDTO>();
		
		for(TemaEntity t : temas) {
			AlvoTemaDTO dto = new AlvoTemaDTO();
			dto.setLabel(t.getNmTema());
			dto.setChildren(entityListToPerguntaDTOList(t.getPerguntas()));
			alvoTemas.add(dto);
		}
		
		return alvoTemas;
	}

	private ArrayList<AlvoPerguntaDTO> entityListToPerguntaDTOList(List<PerguntaEntity> perguntas) {

		ArrayList<AlvoPerguntaDTO> alvoPerguntas = new ArrayList<AlvoPerguntaDTO>();
		
		for(PerguntaEntity p : perguntas) {
			AlvoPerguntaDTO dto = new AlvoPerguntaDTO();
			dto.setCdPergunta(p.getCdPergunta().intValue());
			dto.setLabel(p.getDescPergunta());
			dto.setPeso(p.getPeso());
			alvoPerguntas.add(dto);
		}
		
		return alvoPerguntas;
	}

	public Integer calculaNotas(List<CamadaEntity> camadas, Long cdAvaliacao) {
		


		for(CamadaEntity c : camadas) {
			Integer notaCamada = 0;
			for(TemaEntity t : c.getTemas()) {
				Integer notaTema = 0;
				Integer somaPeso = 0;
				for(PerguntaEntity p : t.getPerguntas()) {	
					somaPeso += p.getPeso();
					Integer notaPergunta = 0;
					for(RespostaEntity r : p.getRespostas()) {
						if(r.getAvaliacao().getCdAvaliacao() == cdAvaliacao) {
							notaPergunta += r.getNota();
							notaPergunta = notaPergunta * p.getPeso();
						}
					}
					
				}
				
			}
		}
		
		return null;
	}


}
