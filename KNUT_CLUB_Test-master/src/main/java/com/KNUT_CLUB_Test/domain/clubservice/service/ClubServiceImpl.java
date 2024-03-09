package com.KNUT_CLUB_Test.domain.clubservice.service;

import com.KNUT_CLUB_Test.domain.clubservice.Club;
import com.KNUT_CLUB_Test.domain.clubservice.NewClubDTO;
import com.KNUT_CLUB_Test.domain.clubservice.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService{

    private final ClubRepository clubRepository;
    @Override
    public List<Club> getClubList(String campus, String type, String cWord, String tWord) {
        return clubRepository.getClubList(campus, type, cWord, tWord);
    }

    @Override
    public List<Club> getClubDetail(int num) {
        return clubRepository.getClubDetail(num);
    }

    @Override
    public void joinClub(String id, String club, String motive) {
        clubRepository.joinClub(id, club, motive);
    }

    @Override
    public boolean createClub(NewClubDTO dto) {
        return clubRepository.createClub(dto);
    }

    @Override
    public void uploadClubImg(String file, String clubName) {
        clubRepository.uploadClubImg(file, clubName);
    }
}
