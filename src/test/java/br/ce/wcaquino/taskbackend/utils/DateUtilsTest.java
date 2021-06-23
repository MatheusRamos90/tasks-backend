package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		assertFalse(DateUtils.isEqualOrFutureDate(LocalDate.of(2030, 01, 01)));
	}
	
	@Test
	public void deveRetornarFalsoParaDatasPassadas() {
		assertFalse(DateUtils.isEqualOrFutureDate(LocalDate.of(2010, 01, 01)));
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual() {
		assertTrue(DateUtils.isEqualOrFutureDate(LocalDate.now()));
	}

}
