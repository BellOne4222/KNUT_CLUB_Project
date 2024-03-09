package com.KNUT_CLUB_Test.domain.eventsrvice.service;

import com.KNUT_CLUB_Test.domain.eventsrvice.Event;
import com.KNUT_CLUB_Test.domain.eventsrvice.EventPostDTO;
import com.KNUT_CLUB_Test.domain.eventsrvice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Override
    public List<Event> getEventList(String field, String field2, String query, String query2, int page) {
        return eventRepository.getEventList(field, field2, query, query2, page);
    }

    @Override
    public List<Event> getEventDetail(int num) {
        return eventRepository.getEventDetail(num);
    }

    @Override
    public boolean getEventWrite(EventPostDTO dto) {
        return eventRepository.getEventWrite(dto);
    }

    @Override
    public void uploadEventImg(String file, String clubName) {
        eventRepository.uploadEventImg(file, clubName);
    }
}
