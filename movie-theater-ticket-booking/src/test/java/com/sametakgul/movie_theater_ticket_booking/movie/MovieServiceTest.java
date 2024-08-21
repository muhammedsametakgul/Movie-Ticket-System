package com.sametakgul.movie_theater_ticket_booking.movie;

import com.sametakgul.movie_theater_ticket_booking.entity.enums.Genre;
import com.sametakgul.movie_theater_ticket_booking.entity.enums.Language;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Movie;
import com.sametakgul.movie_theater_ticket_booking.entity.model.Show;
import com.sametakgul.movie_theater_ticket_booking.entity.response.MovieResponse;
import com.sametakgul.movie_theater_ticket_booking.mapper.MovieMapper;
import com.sametakgul.movie_theater_ticket_booking.repository.MovieRepository;
import com.sametakgul.movie_theater_ticket_booking.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    Show show1;
    Show show2;
    List<Show> showList = new ArrayList<>();
    @BeforeEach
    public void setUp() {
        show1 = Show.builder()
                .time(Time.valueOf("14:00:00"))
                .date(Date.valueOf("2024-08-22"))
                .build();

        show2 = Show.builder()
                .time(Time.valueOf("18:00:00"))
                .date(Date.valueOf("2024-08-22"))
                .build();

        showList.add(show1);
        showList.add(show2);
    }

    @Test
    @DisplayName("Get All Movies")
    public void getAllMovies() {


        Movie movie1 = Movie.builder()
                .movieName("TED MOSSBY")
                .duration(148)
                .rating(8.8)
                .releaseDate(new java.util.Date())
                .genre(Genre.SPORTS)
                .language(Language.ENGLISH)
                .shows(showList)
                .build();

        Movie movie2 = Movie.builder()
                .movieName("DeadPool")
                .duration(148)
                .rating(8.8)
                .releaseDate(new java.util.Date())
                .genre(Genre.COMEDY)
                .language(Language.ENGLISH)
                .shows(showList)
                .build();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);
        //when
        when(movieRepository.findAll()).thenReturn(movieList);


        // Act
        List<MovieResponse> result = movieService.getAllMovies();

        // Assert
        assertEquals(2, result.size());
        assertEquals("TED MOSSBY", result.get(0).getMovieName());
        assertEquals("DeadPool", result.get(1).getMovieName());

        //verify
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void getMovieById_MovieExists_ReturnsMovieResponse(){

        Integer movieId = 1;

        Movie movie = Movie.builder()
                .id(movieId)
                .movieName("Inception")
                .duration(148)
                .rating(8.8)
                .releaseDate(new java.util.Date())
                .genre(Genre.COMEDY)
                .language(Language.ENGLISH)
                .shows(showList)
                .build();

        //Mapping
        MovieResponse expectedResponse = MovieMapper.movieToMovieResponse(movie);

        //When
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        //Act
        MovieResponse result = movieService.getMovieById(movieId);

        //Assertion
        assertEquals(expectedResponse,result);

    }

    @Test
    public void getMovieById_MovieDoesNotExist_ReturnsMovieResponse(){
        Integer movieId = 1;

        //When
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        //Act
        MovieResponse result = movieService.getMovieById(movieId);

        //Assertion
        assertNull(result);
    }
}
