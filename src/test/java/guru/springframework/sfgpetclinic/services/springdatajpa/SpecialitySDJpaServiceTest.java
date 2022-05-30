package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;


    @DisplayName("Test find by id without bdd mockito style")
    @Test
    void findByIdTest() {
        // given - precondition/setup
        Speciality speciality = new Speciality(1L, "mocked-speciality");

        // when - action or the behaviour that we are going to test
        when(specialtyRepository.findById(speciality.getId())).thenReturn(Optional.of(speciality));
        Speciality foundSpecialty = service.findById(speciality.getId());

        // then - verify the output
        assertThat(foundSpecialty).isSameAs(speciality);
        verify(specialtyRepository).findById(1L);
    }

    @DisplayName("Test find by id with bdd mockito style")
    @Test
    void findByIdBddTest() {
        // given - precondition/setup
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when - action or the behaviour that we are going to test
        Speciality foundSpecialty = service.findById(1L);

        // then - verify the output
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @DisplayName("Test delete by object with bdd mockito style")
    @Test
    void testDeleteByObject() {
        // given - precondition/setup
        Speciality speciality = new Speciality();

        // when - action or the behaviour that we are going to test
        service.delete(speciality);

        // then - verify the output
        then(specialtyRepository).should().delete(any(Speciality.class));
    }


    @DisplayName("Test delete by id with bdd mockito style")
    @Test
    void deleteById() {
        // when - action or the behaviour that we are going to test
        service.deleteById(1l);
        service.deleteById(1l);

        // then - verify the output
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @DisplayName("Test delete by id at least with bdd mockito style")
    @Test
    void deleteByIdAtLeast() {
        // when - action or the behaviour that we are going to test
        service.deleteById(1l);
        service.deleteById(1l);

        // then - verify the output
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @DisplayName("Test delete by id at most with bdd mockito style")
    @Test
    void deleteByIdAtMost() {
        // when - action or the behaviour that we are going to test
        service.deleteById(1l);
        service.deleteById(1l);

        // then - verify the output
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @DisplayName("Test delete by id never with bdd mockito style")
    @Test
    void deleteByIdNever() {
        // when - action or the behaviour that we are going to test
        service.deleteById(1l);
        service.deleteById(1l);

        // then - verify the output
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @DisplayName("Test delete by with bdd mockito style")
    @Test
    void testDelete() {
        // when - action or the behaviour that we are going to test
        service.delete(new Speciality());

        // then - verify the output
        then(specialtyRepository).should().delete(any());
    }
}
