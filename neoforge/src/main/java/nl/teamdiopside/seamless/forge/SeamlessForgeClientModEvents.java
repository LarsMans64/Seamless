package nl.teamdiopside.seamless.forge;

//@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Seamless.MOD_ID)
public class SeamlessForgeClientModEvents {

//    @SubscribeEvent
//    public static void addPack(AddPackFindersEvent event) {
//        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
//            try {
//                ResourceLocation folderName = ResourceLocation.fromNamespaceAndPath(Seamless.MOD_ID, Seamless.RESOURCE_PACK);
//
//                IModFile file = ModList.get().getModFileById(folderName.getNamespace()).getFile();
//
//                PackLocationInfo location = new PackLocationInfo(folderName.toString(), Component.translatable("seamless.resource_pack"), PackSource.BUILT_IN, Optional.empty());
//
//                PathPackResources pack = new PathPackResources(location, file.findResource("resourcepacks/" + folderName.getPath()));
//
//                var metadata = Objects.requireNonNull(pack.getMetadataSection(PackMetadataSection.TYPE));
//
//                event.addRepositorySource(consumer -> consumer.accept(Pack.readMetaAndCreate(
//                        location,
//                        new Pack.ResourcesSupplier() {
//                            @Override
//                            public PackResources openPrimary(PackLocationInfo arg) {
//                                return pack;
//                            }
//
//                            @Override
//                            public PackResources openFull(PackLocationInfo arg, Pack.Metadata arg2) {
//                                return pack;
//                            }
//                        },
//                        PackType.CLIENT_RESOURCES,
//                        new PackSelectionConfig(false, Pack.Position.TOP, false)
//                )));
//            } catch (Exception e) {
//                Seamless.LOGGER.error("Failed to add Built-in Seamless resources");
//            }
//        }
//    }
}
