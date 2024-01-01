public class RendererFactory {
    private final String CONSOLE_RENDERER="console";
    private final String NO_RENDERER="none";
    public Renderer buildRenderer(String rendereString) {
        switch (rendereString) {
            case CONSOLE_RENDERER:
                return new ConsoleRenderer();
            case NO_RENDERER:
                return new VoidRenderer();
            default:
                    return new VoidRenderer();
        }
    }
}
