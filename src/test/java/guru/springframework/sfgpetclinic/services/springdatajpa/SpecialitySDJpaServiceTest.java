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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

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

    @DisplayName("Test find by id with bdd")
    @Test
    void findByIdBddTest() {
        // given - precondition/setup
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when - action or the behaviour that we are going to test
        Speciality foundSpecialty = service.findById(1L);

        // then - verify the output
        assertThat(foundSpecialty).isNotNull();
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void testDeleteByObject() {
        // given - precondition/setup
        Speciality speciality = new Speciality();

        // when - action or the behaviour that we are going to test
        service.delete(speciality);

        // then - verify the output
        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}
