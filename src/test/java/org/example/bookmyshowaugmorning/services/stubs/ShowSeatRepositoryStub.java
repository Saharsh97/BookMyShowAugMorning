package org.example.bookmyshowaugmorning.services.stubs;

import org.example.bookmyshowaugmorning.models.ShowSeat;
import org.example.bookmyshowaugmorning.repositories.ShowSeatRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ShowSeatRepositoryStub implements ShowSeatRepository {

    List<ShowSeat> hardcodedShowSeats;

    public ShowSeatRepositoryStub(List<ShowSeat> hardcodedShowSeats) {
        this.hardcodedShowSeats = hardcodedShowSeats;
    }

    @Override
    public List<ShowSeat> save(List<ShowSeat> showSeats) {
        int idx = 200;
        for(ShowSeat showSeat : showSeats) {
            showSeat.setId(idx++);
        }
        return showSeats;
    }

    @Override
    public List<ShowSeat> findAllById(Iterable<Long> showSeatIDS) {
        return hardcodedShowSeats;
    }

    // ------------------------------------------------------------------ //

    @Override
    public <S extends ShowSeat> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ShowSeat> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<ShowSeat> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<ShowSeat> findAll() {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(ShowSeat entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ShowSeat> entities) {

    }

    @Override
    public void deleteAll() {

    }



    @Override
    public void flush() {

    }

    @Override
    public <S extends ShowSeat> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ShowSeat> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ShowSeat> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ShowSeat getOne(Long aLong) {
        return null;
    }

    @Override
    public ShowSeat getById(Long aLong) {
        return null;
    }

    @Override
    public ShowSeat getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ShowSeat> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ShowSeat> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ShowSeat> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ShowSeat> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ShowSeat> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ShowSeat> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ShowSeat, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<ShowSeat> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ShowSeat> findAll(Pageable pageable) {
        return null;
    }
}
