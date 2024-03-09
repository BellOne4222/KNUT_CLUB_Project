package com.KNUT_CLUB_Test.web.event;



import com.KNUT_CLUB_Test.domain.eventsrvice.Event;
import com.KNUT_CLUB_Test.domain.eventsrvice.EventPostDTO;
import com.KNUT_CLUB_Test.domain.eventsrvice.service.EventService;
import com.KNUT_CLUB_Test.file.FileStore;
import com.KNUT_CLUB_Test.web.form.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final FileStore fileStore;

    @GetMapping("/event")
    public String goEvent(@RequestParam(value = "select", required = false) String field_,
                          @RequestParam(value = "campus", required = false) String query_,
                          @RequestParam(value = "type", required = false) String query2_,
                          @RequestParam(value = "p", required = false) String page_, Model model) {

        String field = "campus";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String field2 = "type";
        if (field_ != null && !field_.equals(""))
            field = field_;

        String query = "";
        if (query_ != null && !query_.equals(""))
            query = query_;

        String query2 = "";
        if (query2_ != null && !query2_.equals(""))
            query2 = query2_;

        int page = 1;
        if (page_ != null && !page_.equals(""))
            page = Integer.parseInt(page_);

        List<Event> list = eventService.getEventList(field, field2, query, query2, page);

        model.addAttribute("list", list);
        model.addAttribute("page", page_);
        return "event/event";
    }

    @GetMapping("/event/detail")
    public String goClubDetail(@RequestParam("num") int num,
                               Model model) {

        List<Event> eventList = eventService.getEventDetail(num);
        model.addAttribute("eventList", eventList);

        return "/event/detail/eventDetail";
    }

    @GetMapping("/event/write")
    public String goEventWrite(Model model) {

        model.addAttribute("eventPost", new EventPostDTO());

        return "/event/newEvent";
    }

    @PostMapping("/event/write")
    public String doEventWrite(@ModelAttribute("eventPost") EventPostDTO dto,
                               @RequestParam("attachFile") MultipartFile file,
                               Model model) throws IOException {

        boolean check = eventService.getEventWrite(dto);

        String eventName = dto.getName();
        System.out.println("eventName = " + eventName);

        /* upload */
        UploadFile attachFile = fileStore.storeFile(file, "");

        String filename = fileStore.createStoreFileName(file.getOriginalFilename());
        String fullPath = "/attachFile/" + filename;

        eventService.uploadEventImg(fullPath, eventName);

        if (check == true) {
            model.addAttribute("message", "게시글이 작성되었습니다.");
            model.addAttribute("url", "/index");
        }
        else {
            model.addAttribute("message", "필수 정보가 비어있습니다.");
            model.addAttribute("url", "/event/newEvent");
        }

        return "/alert";
    }
}
