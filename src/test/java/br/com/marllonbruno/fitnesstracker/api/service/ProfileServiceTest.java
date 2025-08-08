package br.com.marllonbruno.fitnesstracker.api.service;

import br.com.marllonbruno.fitnesstracker.api.entity.User;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.ActivityLevel;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Gender;
import br.com.marllonbruno.fitnesstracker.api.entity.enuns.Objective;
import br.com.marllonbruno.fitnesstracker.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Anotação para habilitar o Mockito
class ProfileServiceTest {

    // Cria um "dublê" para o UserRepository. Ele não fará chamadas reais ao banco de dados
    @Mock
    private UserRepository userRepository;

    // Cria uma instância real do ProfileService e injeta o mock do UserRepository nela
    @InjectMocks
    private ProfileService profileService;

    private User testUser;
    private User testUserFemale;

    // Método de setup, roda antes de cada teste
    @BeforeEach
    void setUp() {
        testUser = new User();
        // Configura um usuário de teste padrão
        testUser.setBirthDate(LocalDate.of(1990, 1, 1));
        testUser.setHeightCm(180);
        testUser.setCurrentWeightKg(80.0);
        testUser.setGender(Gender.MALE);
        testUser.setActivityLevel(ActivityLevel.MODERATELY_ACTIVE);
        testUser.setObjective(Objective.MAINTAIN_WEIGHT);

        // Inicializa o usuário feminino para testes
        testUserFemale = new User();
        testUserFemale.setBirthDate(LocalDate.of(2000, 12, 10));
        testUserFemale.setHeightCm(165);
        testUserFemale.setCurrentWeightKg(65.0);
        testUserFemale.setGender(Gender.FEMALE);
        testUserFemale.setActivityLevel(ActivityLevel.ACTIVE);
        testUserFemale.setObjective(Objective.MAINTAIN_WEIGHT);
    }

    @Test
    void deveCalcularMetricasCorretamenteParaUsuarioMasculino() {
        // AAA - Padrão de escrita de testes: Arrange, Act, Assert

        // Arrange (Arranjo)
        // O usuário já foi configurado no método setUp().
        // Cálculo manual esperado para a TMB (Fórmula de Mifflin-St Jeor):
        // BMR = 10 * 80 + 6.25 * 180 - 5 * 35 + 5
        // BMR = 800 + 1125 - 175 + 5 = 1755
        double expectedBMR = 1755.0;

        // Cálculo manual esperado para o GET/TDEE (Moderadamente Ativo = 1.55)
        // TDEE = 1755 * 1.55 = 2720.25
        int expectedTDEE = 2720; // O método arredonda para int

        // Act (Ação)
        // Como os métodos de cálculo são privados, testamos através de um método público que os chame.
        // Vamos precisar expor a lógica ou testar via `updateUserProfileAndMetrics`.
        // Para facilitar, vamos criar um método público no serviço só para orquestrar os cálculos.
        profileService.calculateAllMetrics(testUser);

        // Assert (Verificação)
        // Verificamos se os valores calculados no objeto testUser correspondem aos esperados.
        assertNotNull(testUser.getTmb());
        assertEquals(expectedBMR, testUser.getTmb(), 0.01); // 0.01 é a margem de erro para doubles

        assertNotNull(testUser.getDailyCaloriesGoal());
        assertEquals(expectedTDEE, testUser.getDailyCaloriesGoal());

        // Verificação dos Macros (baseado em 2720 kcal e 40/30/30)
        // Carbs: (2720 * 0.4) / 4 = 272
        // Protein: (2720 * 0.3) / 4 = 204
        // Fat: (2720 * 0.3) / 9 = 90
        assertEquals(272, testUser.getDailyCarbsGoal());
        assertEquals(204, testUser.getDailyProteinGoal());
        assertEquals(90, testUser.getDailyFatGoal());
    }

    @Test
    void deveCalcularMetricasCorretamenteParaUsuarioFeminino() {
        // AAA - Padrão de escrita de testes: Arrange, Act, Assert

        // Arrange (Arranjo)
        // O usuário já foi configurado no método setUp().
        // Cálculo manual esperado para a TMB (Fórmula de Mifflin-St Jeor):
        // 10 * user.getCurrentWeightKg() + 6.25 * user.getHeightCm() - 5 * age - 161;
        // BMR = 10 * 65 + 6.25 * 165 - 5 * 24 - 161
        // BMR = 650 + 1.031,25 - 120 - 161 = 1.400,25
        double expectedBMR = 1400.25;

        // Cálculo manual esperado para o GET/TDEE (Moderadamente Ativo = 1.55)
        // TDEE = 1400.25 * 1.725 = 2.415,43
        int expectedTDEE = 2415; // O método arredonda para int

        // Act (Ação)
        // Como os métodos de cálculo são privados, testamos através de um método público que os chame.
        // Vamos precisar expor a lógica ou testar via `updateUserProfileAndMetrics`.
        // Para facilitar, vamos criar um método público no serviço só para orquestrar os cálculos.
        profileService.calculateAllMetrics(testUserFemale);

        // Assert (Verificação)
        // Verificamos se os valores calculados no objeto testUser correspondem aos esperados.
        assertNotNull(testUserFemale.getTmb());
        assertEquals(expectedBMR, testUserFemale.getTmb(), 0.01); // 0.01 é a margem de erro para doubles

        assertNotNull(testUserFemale.getDailyCaloriesGoal());
        assertEquals(expectedTDEE, testUserFemale.getDailyCaloriesGoal());

        // Verificação dos Macros (baseado em 2720 kcal e 40/30/30)
        // Carbs: (2415 * 0.4) / 4 = 241
        // Protein: (2415 * 0.3) / 4 = 181
        // Fat: (2415 * 0.3) / 9 = 80
        assertEquals(241, testUserFemale.getDailyCarbsGoal());
        assertEquals(181, testUserFemale.getDailyProteinGoal());
        assertEquals(80, testUserFemale.getDailyFatGoal());
    }

}
