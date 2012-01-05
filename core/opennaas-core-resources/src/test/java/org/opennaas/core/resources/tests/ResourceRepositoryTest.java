package org.opennaas.core.resources.tests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Hashtable;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.ResourceException;
import org.opennaas.core.resources.ResourceRepository;
import org.opennaas.core.resources.capability.ICapabilityFactory;
import org.opennaas.core.resources.descriptor.ResourceDescriptor;
import org.opennaas.core.resources.tests.helpers.mocks.descriptor.MockResourceDescriptorRepository;
import org.opennaas.core.resources.tests.helpers.mocks.descriptor.ResourceDescriptorFactory;

/**
 * Test class for the ResourceRepository class
 * 
 * @author Scott Campbell (CRC)
 * 
 */
public class ResourceRepositoryTest {
	/*
	 * The class under test - actually, it is a class that extends the class under test but adds no new functionality
	 */
	private static ResourceRepository	resourceRepository		= null;
	/* Use a mock module factory */
	private static ICapabilityFactory	mockCapabilityFactory	= null;

	/* Descriptor factory */
	ResourceDescriptorFactory			descriptorFactory		= new ResourceDescriptorFactory();

	@BeforeClass
	public static void setup() {
		mockCapabilityFactory = createMock(ICapabilityFactory.class);
		Map<String, ICapabilityFactory> capabilityFactories = new Hashtable<String, ICapabilityFactory>();
		capabilityFactories.put("MockCapability", mockCapabilityFactory);
		resourceRepository = new ResourceRepository("Mock", "", capabilityFactories);
		resourceRepository.setResourceDescriptorRepository(new MockResourceDescriptorRepository());
	}

	@Test
	public void testCreateResource() {
		try {
			replay(mockCapabilityFactory);
			// Run the test
			ResourceDescriptor descriptor = descriptorFactory.newMockResourceDescriptor("Resource Mock Create");
			resourceRepository.createResource(descriptor);

			verify(mockCapabilityFactory);

			// check that the expected resource is the one in the list
			IResource resource = resourceRepository.getResource(descriptor.getId());
			assertNotNull(resource);

			// check that the resource descriptor has been added to the resource
			assertNotNull(resource.getResourceDescriptor());
			assertTrue(descriptor.equals(resource.getResourceDescriptor()));

		} catch (ResourceException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testModifyResource() {

		try {
			ResourceDescriptor descriptor = descriptorFactory.newMockResourceDescriptor("Resource Mock Modify");
			resourceRepository.createResource(descriptor);

			ResourceDescriptor descriptor2 = descriptorFactory.newMockResourceDescriptor("Mock Resource WORKS");

			assertNotNull(descriptor.getId());

			resourceRepository.modifyResource(descriptor.getId(), descriptor2);
			// check that the expected resource is the one in the list
			IResource resource = resourceRepository.getResource(descriptor.getId());

			assertNotNull(resource);
			assertTrue(resource.getResourceDescriptor().getInformation().getName().equalsIgnoreCase("Mock Resource WORKS"));

		} catch (ResourceException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void testRemoveResource() {
		try {

			int previousSize = resourceRepository.listResources().size();

			ResourceDescriptor descriptor = descriptorFactory.newMockResourceDescriptor("Resource Mock CreateRemove");
			resourceRepository.createResource(descriptor);
			resourceRepository.removeResource(descriptor.getId());

			assertTrue(resourceRepository.listResources().size() == previousSize);

		} catch (ResourceException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test(expected = ResourceException.class)
	public void testEngineTypeNotInConfig() throws ResourceException {
		ResourceDescriptor descriptor = descriptorFactory.newMockResourceDescriptor("Resource Mock Modify");
		descriptor.getInformation().setType("WrongEngine");
		resourceRepository.createResource(descriptor);
	}

}
