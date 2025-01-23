package com.gilandrey.dslist.services;

import com.gilandrey.dslist.dto.GameDTO;
import com.gilandrey.dslist.dto.GameListDTO;
import com.gilandrey.dslist.dto.GameMinDTO;
import com.gilandrey.dslist.entities.Game;
import com.gilandrey.dslist.entities.GameList;
import com.gilandrey.dslist.projections.GameMinProjection;
import com.gilandrey.dslist.repositories.GameListRepository;
import com.gilandrey.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(GameListDTO::new).toList();
    }

    // List for move games configuration, (intention is can move one game forward or backward from another game)
    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}
