package com.pleiterson.heroesapispringboot;

import static com.pleiterson.heroesapispringboot.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import com.pleiterson.heroesapispringboot.repository.HeroesRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiSpringBootApplicationTests {
	@Autowired
	WebTestClient webTestClient;
  
	@Autowired
	HeroesRepository heroesRepository;

	@Test
  public void getOneHeroeById(){
    webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
      .exchange()
      .expectStatus().isOk()
      .expectBody();
  }

  @Test
  public void getOneHeronotFound(){
    webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"10")
      .exchange()
      .expectStatus().isNotFound();
  }

  @Test
  public void deleteHero(){
    webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"1")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus().isNotFound()
      .expectBody(Void.class);
  }
}
