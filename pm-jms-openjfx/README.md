# If you want it to work

## How To with Apache NetBeans 11

Right-click on the project and select the Properties item, then click on the Run item.

Finally, enter text similar to the one below in the VM Options space:
```
--module-path="<your path>/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib"
--add-modules="javafx.controls,javafx.fxml"
```

Of course the path to be assigned to --module-path will depend on where you installed the library in question.
