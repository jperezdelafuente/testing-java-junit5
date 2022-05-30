package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetControllerTest implements ControllerTests {

    @Mock
    VetService vetService;
    @InjectMocks
    VetController vetController;
    Vet vet1;
    Vet vet2;

    @BeforeEach
    void setUp() {

        vet1 = new Vet(1L, "joe", "buck", null);
        vet2 = new Vet(2L, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        // given - precondition/setup
        Set<Vet> vets = new HashSet<>();
        vets.add(vet1);
        vets.add(vet2);

        // when - action or the behaviour that we are going to test
        when(this.vetService.findAll()).thenReturn(vets);
        Model model = new ModelMapImpl();
        String view = this.vetController.listVets(model);

        // then - verify the output
        verify(vetService).findAll();
        assertThat(view).isEqualTo("vets/index");
        Map<String, Object> map = ((ModelMapImpl) model).getMap();
        assertThat(map).containsKey("vets").containsValue(vets);
    }
}