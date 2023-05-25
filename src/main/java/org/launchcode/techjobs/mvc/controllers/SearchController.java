package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm)
    {
        ArrayList<Job> jobs = new ArrayList<>();
        if (Objects.equals(searchTerm, "all"))
        {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        model.addAttribute("searchType", searchType);

        return "search";
    }
}
//        I think this works? - Use the correct annotation for the method. To configure the correct mapping type and mapping route, refer to the form tag in the search.html template. (Use @GetMapping or @PostMapping rather than @RequestMapping.)
//        Done - the displaySearchResults method should take in a Model parameter.
//        Done - The method should also take in two other parameters, specifying the type of search and the search term.
//        Done - In order for these last two parameters to be properly passed in by Spring Boot, you need to use the correct annotation. Also, you need to name them appropriately, based on the corresponding form field names defined in search.html.
//        Done - If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.
//        Done - Pass jobs into the search.html view via the model parameter.
//        Done - Pass ListController.columnChoices into the view, as the existing search handler does.

