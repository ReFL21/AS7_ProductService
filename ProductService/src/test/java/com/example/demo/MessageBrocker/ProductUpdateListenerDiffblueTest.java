package com.example.demo.MessageBrocker;

import com.example.demo.MessageBrocker.ProductUpdateEvent.ProductUpdateEventBuilder;
import com.example.demo.business.IUpdateProduct;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductUpdateListener.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class ProductUpdateListenerDiffblueTest {
    @MockitoBean
    private IUpdateProduct iUpdateProduct;

    @Autowired
    private ProductUpdateListener productUpdateListener;

    /**
     * Test {@link ProductUpdateListener#handle(ProductUpdateEvent)}.
     * <ul>
     *   <li>When builder quantities {@link HashMap#HashMap()} build.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductUpdateListener#handle(ProductUpdateEvent)}
     */
    @Test
    @DisplayName("Test handle(ProductUpdateEvent); when builder quantities HashMap() build")
    @Tag("MaintainedByDiffblue")
    void testHandle_whenBuilderQuantitiesHashMapBuild() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        ProductUpdateEventBuilder builderResult = ProductUpdateEvent.builder();
        ProductUpdateEvent event = builderResult.quantities(new HashMap<>()).build();

        // Act
        productUpdateListener.handle(event);
    }

    /**
     * Test {@link ProductUpdateListener#handle(ProductUpdateEvent)}.
     * <ul>
     *   <li>When {@link ProductUpdateEvent#ProductUpdateEvent()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ProductUpdateListener#handle(ProductUpdateEvent)}
     */
    @Test
    @DisplayName("Test handle(ProductUpdateEvent); when ProductUpdateEvent()")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByDiffblue")
    void testHandle_whenProductUpdateEvent() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.forEach(java.util.function.BiConsumer)" because "that" is null
        //       at com.example.demo.MessageBrocker.ProductUpdateListener.handle(ProductUpdateListener.java:16)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        productUpdateListener.handle(new ProductUpdateEvent());
    }
}
