package org.example.bookmyshowaugmorning.services.stubs;

import org.example.bookmyshowaugmorning.models.Booking;
import org.example.bookmyshowaugmorning.repositories.BookingRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BookingRepositoryStub implements BookingRepository {
    @Override
    public Booking save(Booking booking) {
        booking.setId(50000);
        return booking;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Booking> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Booking> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Booking> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Booking getOne(Long aLong) {
        return null;
    }

    @Override
    public Booking getById(Long aLong) {
        return null;
    }

    @Override
    public Booking getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Booking> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Booking> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Booking> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Booking> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Booking, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Booking> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Booking> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Booking> findAll() {
        return List.of();
    }

    @Override
    public List<Booking> findAllById(Iterable<Long> longs) {
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
    public void delete(Booking entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Booking> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Booking> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return null;
    }
}
