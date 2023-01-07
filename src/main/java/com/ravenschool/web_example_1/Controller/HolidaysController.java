package com.ravenschool.web_example_1.Controller;

import com.ravenschool.web_example_1.Model.Holiday;
import com.ravenschool.web_example_1.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/holidays"})
public class HolidaysController {

    private final HolidayService _holidayService;

    @Autowired
    public HolidaysController(HolidayService holidayService) {
        this._holidayService = holidayService;
    }

    @GetMapping(value = {"/{display}"})
    public String displayHolidays(Model model, @PathVariable() String display) {
        if (display != null) {
            switch (display) {
                case "festival":
                    model.addAttribute("festival", true);
                    break;
                case "federal":
                    model.addAttribute("federal", true);
                    break;
                default:
                    model.addAttribute("federal", true);
                    model.addAttribute("festival", true);
            }
        }

        List<Holiday> holidays = _holidayService.getAllHolidays();

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
