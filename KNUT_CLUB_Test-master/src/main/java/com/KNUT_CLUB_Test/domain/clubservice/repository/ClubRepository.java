package com.KNUT_CLUB_Test.domain.clubservice.repository;

import com.KNUT_CLUB_Test.domain.clubservice.Club;
import com.KNUT_CLUB_Test.domain.clubservice.NewClubDTO;

import java.util.List;

public interface ClubRepository {

    List<Club> getClubList(String campus, String type, String cWord, String tWord);

    List<Club> getClubDetail(int num);

    void joinClub(String id, String club, String motive);

    boolean createClub(NewClubDTO dto);

    void uploadClubImg(String file, String clubName);

}
