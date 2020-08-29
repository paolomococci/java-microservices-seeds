/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.seed.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import local.example.seed.layout.MainLayout;

@Route(layout = MainLayout.class)
@PageTitle(value = "main view")
@RouteAlias(value = "main", layout = MainLayout.class)
public class MainView
        extends Main {

    public MainView() {
        super();
        Paragraph paragraph = new Paragraph();
        H2 subtitle = new H2("access through a RESTful web service");
        paragraph.add("open the navigation bar and click on the links");
        Section mainSection = new Section(subtitle, paragraph);
        this.add(mainSection);
    }
}
