package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;


    @DisplayName("Test Find All")
    @Test
    void findAll() {
        // given - precondition/setup
        Visit visit1 = new Visit();
        Visit visit2 = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit1);
        visits.add(visit2);

        // when - action or the behaviour that we are going to test
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundVisits = service.findAll();

        // then - verify the output
        verify(visitRepository).findAll();
        assertThat(foundVisits).hasSize(2);
        assertThat(foundVisits).isEqualTo(visits);
    }

    @DisplayName("Test find by Id")
    @Test
    void findById() {
        // given - precondition/setup
        Visit visit = new Visit();

        // when - action or the behaviour that we are going to test
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);

        // then - verify the output
        verify(visitRepository).findById(anyLong());
        assertThat(foundVisit).isNotNull();
    }

    @DisplayName("Test save")
    @Test
    void save() {
        // given - precondition/setup
        Visit visit = new Visit();

        // when - action or the behaviour that we are going to test
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(new Visit());

        // then - verify the output
        verify(visitRepository).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @DisplayName("Test delete")
    @Test
    void delete() {
        // given - precondition/setup
        Visit visit = new Visit();

        // when - action or the behaviour that we are going to test
        service.delete(visit);

        // then - verify the output
        verify(visitRepository).delete(any(Visit.class));
    }

    @DisplayName("Test delete by id")
    @Test
    void deleteById() {
        // when - action or the behaviour that we are going to test
        service.deleteById(1L);

        // then - verify the output
        verify(visitRepository).deleteById(anyLong());
    }
}