package com.example.demo.business;

import com.example.demo.business.Impl.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.*;
public class ResourceNotFoundExeption {
    @Test
    void exceptionShouldRetainMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException("oops");
        assertEquals("oops", ex.getMessage());
    }

    @Test
    void shouldBeAnnotatedWithNotFoundStatus() {
        ResponseStatus rs = ResourceNotFoundException.class.getAnnotation(ResponseStatus.class);
        assertNotNull(rs, "@ResponseStatus must be present");
        assertEquals(HttpStatus.NOT_FOUND, rs.value());
    }
}
