#!/usr/bin/env bash

start_runcrud()
{
    /Users/marcinpaszenda/IdeaProjects/tasks/runcrud.sh start
    end
}

end() {
   echo "Work is finished, runcrud is started."
}

fail() {
   echo "There were errors with starting runcrud"
}

browser()
{
    open -a "Google Chrome" http://localhost:8080/crud/v1/tasks
}

if start_runcrud; then
    browser
    echo "Success! Browser is opened, URL is working!"
else
    fail
fi



