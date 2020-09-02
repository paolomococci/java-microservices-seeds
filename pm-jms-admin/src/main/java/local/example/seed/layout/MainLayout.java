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

package local.example.seed.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import local.example.seed.view.CustomerView;
import local.example.seed.view.InvoiceView;
import local.example.seed.view.ItemView;
import local.example.seed.view.MainView;
import local.example.seed.view.editor.CustomerEditorView;
import local.example.seed.view.editor.reactive.CustomerReactiveEditorView;

@Push
@CssImport(value = "style.css")
@PWA(enableInstallPrompt = false, name = "jms-admin", shortName = "admin", startPath = "/main")
@Viewport(value = "width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, viewport-fit=cover")
public class MainLayout
        extends AppLayout
        implements AfterNavigationObserver {

    private final H1 title;
    private final RouterLink mainView;
    private final RouterLink customerView;
    private final RouterLink invoiceView;
    private final RouterLink itemView;

    private final RouterLink customerEditorView;
    private final RouterLink invoiceEditorView;
    private final RouterLink itemEditorView;

    private final RouterLink customerReactiveEditorView;
    private final RouterLink invoiceReactiveEditorView;
    private final RouterLink itemReactiveEditorView;

    public MainLayout() {
        super();

        this.title = new H1("reactive RESTful web service data accessing");
        this.mainView = new RouterLink("main view", MainView.class);
        this.customerView = new RouterLink("customer view", CustomerView.class);
        this.invoiceView = new RouterLink("invoice view", InvoiceView.class);
        this.itemView = new RouterLink("item view", ItemView.class);

        this.customerEditorView = new RouterLink("customer editor", CustomerEditorView.class);
        this.invoiceEditorView = new RouterLink("invoice editor", null);
        this.itemEditorView = new RouterLink("item editor", null);

        this.customerReactiveEditorView = new RouterLink("customer reactive editor", CustomerReactiveEditorView.class);
        this.invoiceReactiveEditorView = new RouterLink("invoice reactive editor", null);
        this.itemReactiveEditorView = new RouterLink("item reactive editor", null);

        OrderedList orderedList = new OrderedList(
                new ListItem(this.mainView),
                new ListItem(this.customerView),
                new ListItem(this.invoiceView),
                new ListItem(this.itemView),
                new ListItem(this.customerEditorView),
                new ListItem(this.invoiceEditorView),
                new ListItem(this.itemEditorView),
                new ListItem(this.customerReactiveEditorView),
                new ListItem(this.invoiceReactiveEditorView),
                new ListItem(this.itemReactiveEditorView)
        );

        Header header = new Header(new DrawerToggle(), this.title);
        Nav nav = new Nav(orderedList);

        this.addToNavbar(header);
        this.addToDrawer(nav);
        this.setPrimarySection(Section.DRAWER);
        this.setDrawerOpened(true);
    }

    private RouterLink[] listLinks() {
        return new RouterLink[] {
                this.mainView,
                this.customerView,
                this.invoiceView,
                this.itemView,
                this.customerEditorView,
                this.invoiceEditorView,
                this.itemEditorView,
                this.customerReactiveEditorView,
                this.invoiceReactiveEditorView,
                this.itemReactiveEditorView
        };
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        for (final RouterLink routerLink : this.listLinks()) {
            if (routerLink.getHighlightCondition().shouldHighlight(
                    routerLink,
                    afterNavigationEvent
            )) {
                this.title.setText(routerLink.getText());
            }
        }
    }
}
