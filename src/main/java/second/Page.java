package second;

import java.util.Collections;

public final class Page implements Action {
    private final String path;

    public Page(String path) {
        this.path = path;
    }

    @Override
    public Outcome act(Income income) {
        return income.render(new Response<View>() {
            @Override
            public Iterable<String> headers() {
                return Collections.emptyList();
            }

            @Override
            public View body() {
                return new View() {
                    @Override
                    public String name() {
                        return Page.this.path;
                    }

                    @Override
                    public Iterable<String> attributes() {
                        return Collections.emptyList();
                    }
                };
            }
        });
    }
}
