package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo todoRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto!");
		} catch (Exception e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		Task todo = new Task();
		todo.setTask("Descrição");
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto!");
		} catch (Exception e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDatasPassadas() {
		Task todo = new Task();
		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		try {
			controller.save(todo);
			fail("Não deveria chegar nesse ponto!");
		} catch (Exception e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		verify(todoRepo).save(todo);
	}
}
