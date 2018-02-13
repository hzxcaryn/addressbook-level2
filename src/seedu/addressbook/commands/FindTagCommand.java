package seedu.addressbook.commands;


import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are tagged by all of "
            + "the specified tags (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAGS [MORE_TAGS]...\n"
            + "Example: " + COMMAND_WORD + " classmate female";

    private final Set<Tag> tags;

    public FindTagCommand(Set<String> inputTags) {
        Set<Tag> tagSet = new HashSet<>();
        for (String tagName : inputTags) {
            try {
                tagSet.add(new Tag(tagName));
            } catch (IllegalValueException e) {
                // Do not add invalid Tag
            }
        }
        this.tags = tagSet;
    }

    /**
     * Returns a copy of tags in this command.
     */
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithMatchingTags(tags);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose tags matches the specified keywords.
     *
     * @param inputTags for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithMatchingTags(Set<Tag> inputTags) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final UniqueTagList personTagList = person.getTags();
            boolean isPerfectMatch = true;
            for (Tag tag : inputTags) {
                if (!personTagList.contains(tag)) {
                    isPerfectMatch = false;
                    break;
                }
            }
            if (isPerfectMatch) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
