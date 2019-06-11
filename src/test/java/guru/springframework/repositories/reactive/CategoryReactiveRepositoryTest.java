package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    public static final String CATEGORY_NAME = "SomeCategory";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {

        Category category = new Category();
        category.setDescription(CATEGORY_NAME);

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();
        assertEquals(count, Long.valueOf(1L));

    }

    @Test
    public void testfindByDescription() throws Exception {

        Category category = new Category();
        category.setDescription(CATEGORY_NAME);

        categoryReactiveRepository.save(category).then().block();

        Category retrievedCategory = categoryReactiveRepository.findByDescription(CATEGORY_NAME).block();

        assertNotNull(retrievedCategory.getId());

    }
}