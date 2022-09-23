package com.example.demo;
import com.example.demo.configuration.test.DemoUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {
    static DemoUtils test;


    @BeforeAll
    public static void setup() {
        test=new DemoUtils();
    }
    @AfterAll
    public static void AfterAll() {
        // delete object
    }
    @BeforeEach
    public void BeforeEach() {
        test=new DemoUtils();
    }
    @AfterEach
    public void AfterEach() {
        // delete object

    }

    @Test
    @Order(3)
     void test_equal_values() {
        assertEquals(6,test.add(4,2),"4+2= 6");
        assertNotEquals(7,test.add(4,2),"2 4+2= 6");
    }



    @Test
    //@Disabled
    void test_same_values() {
        assertSame(test.getAcademy(),test.getAcademyDuplicate(),"null");
        assertNotSame(test.getAcademyDuplicate(),"Luv2Code Academy 2","null");
    }
    @Test
    @Order(1)
   //@DisplayName("my test case Customized name")
    void test_is_Greater() {
        assertTrue(test.isGreater(3,2)," 3 greater than 2");
        assertFalse(test.isGreater(3,5)," 5 greater than 3");
    }


    @Test
    @Order(1)
   // @DisplayName("check null object")
  //  @EnabledOnOs(OS.WINDOWS)
   // @EnabledOnJre(JRE.JAVA_8)

    void test_null_value() {
        assertNull(test.checkNull(null),"null");
    }

    @Test
    @Order(-7)
    @EnabledIfSystemProperty(named = "SYS_PROP",matches = "CI_CD")
    void test_array() {
        String[] test_data = {"A", "B", "C"};
        assertArrayEquals(test_data,test.getFirstThreeLettersOfAlphabet());

    }
    @Test
    @EnabledIfSystemProperty(named = "test",matches = "test")
    void test_array_not_equal() {
        String[] test_data = {"A", "B", "C"};
        assertFalse(Arrays.equals(test_data, test.getAcademyInList().stream().toArray()) );

    }
    @Test
    void test_throw_error() {
        assertThrows(Exception.class,()->test.throwException(-1),"Should throw" );
        assertDoesNotThrow(()->test.throwException(5),"Should not throw" );
    }
    @Test

    void test_time_out() {
        assertTimeoutPreemptively(Duration.ofSeconds(3),()->test.checkTimeout(),"Should noy timeout" );
        assertTimeout(Duration.ofSeconds(3),()->test.checkTimeout(),"Should throw" );
    }
}